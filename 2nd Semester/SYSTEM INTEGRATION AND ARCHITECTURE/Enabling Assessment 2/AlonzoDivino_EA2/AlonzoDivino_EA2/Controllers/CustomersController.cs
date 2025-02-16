using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using AlonzoDivino_EA2.Data;
using AlonzoDivino_EA2.Models;

namespace AlonzoDivino_EA2.Controllers
{
    public class CustomersController : Controller
    {
        private readonly CustomerDbContext _context;
        private readonly InventoryDbContext _inventoryDb;

        public CustomersController(CustomerDbContext context, InventoryDbContext inventoryDb)
        {
            _context = context;
            _inventoryDb = inventoryDb;
        }

        // GET: Customers
        public async Task<IActionResult> Index()
        {
            var customers = await _context.Customers.ToListAsync();
            var customerViewModels = new List<CustomerViewModel>();

            foreach (var customer in customers)
            {
                var inventoryItem = await _inventoryDb.Inventory.FirstOrDefaultAsync(i => i.ItemName == customer.itemName);
                var customerViewModel = new CustomerViewModel
                {
                    Id = customer.Id,
                    customerName = customer.customerName,
                    Address = customer.Address,
                    itemName = customer.itemName,
                    Quantity = customer.Quantity,
                    Price = inventoryItem != null ? (decimal)inventoryItem.Price : 0 
                };
                customerViewModels.Add(customerViewModel);
            }

            return View(customerViewModels);
        }

        // GET: Customers/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var customer = await _context.Customers
                .FirstOrDefaultAsync(m => m.Id == id);
            if (customer == null)
            {
                return NotFound();
            }

            return View(customer);
        }

        public IActionResult Create()
        {
            ViewData["ItemName"] = new SelectList(_inventoryDb.Inventory, "ItemName", "ItemName");
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("Id,customerName,Address,itemName,Quantity")] Customer customer)
        {
            var inventoryItem = await _inventoryDb.Inventory.FirstOrDefaultAsync(i => i.ItemName == customer.itemName);

            if (inventoryItem != null && customer.Quantity > inventoryItem.Quantity)
            {
                ModelState.AddModelError("Quantity", $"The available quantity for {customer.itemName} is {inventoryItem.Quantity}. You cannot order more than that.");
            }

            if (ModelState.IsValid)
            {
                _context.Add(customer);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }

            ViewData["ItemName"] = new SelectList(_inventoryDb.Inventory, "ItemName", "ItemName", customer.itemName);
            return View(customer);
        }

        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var customer = await _context.Customers.FindAsync(id);
            if (customer == null)
            {
                return NotFound();
            }

            ViewBag.ItemName = new SelectList(_inventoryDb.Inventory, "ItemName", "ItemName", customer.itemName);
            return View(customer);
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("Id,customerName,Address,itemName,Quantity")] Customer customer)
        {
            var existingCustomer = await _context.Customers.FindAsync(id);
            if (existingCustomer == null)
            {
                return NotFound();
            }

            var inventoryItem = await _inventoryDb.Inventory.FirstOrDefaultAsync(i => i.ItemName == customer.itemName);

            if (inventoryItem != null)
            {
               
                int quantityDifference = customer.Quantity - existingCustomer.Quantity;
                if (inventoryItem.Quantity + quantityDifference >= 0)
                {
                    inventoryItem.Quantity += quantityDifference;
                }
                else
                {
                    
                    inventoryItem.Quantity = 0; 
                    ModelState.AddModelError("", $"Insufficient stock for {customer.itemName}. Inventory quantity set to 0.");
                }
            }

            if (ModelState.IsValid)
            {
                try
                {
                    existingCustomer.customerName = customer.customerName;
                    existingCustomer.Address = customer.Address;
                    existingCustomer.itemName = customer.itemName;
                    existingCustomer.Quantity = customer.Quantity;

                    _context.Update(existingCustomer);
                    await _context.SaveChangesAsync();
                    await _inventoryDb.SaveChangesAsync(); 
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!CustomerExists(customer.Id))
                    {
                        return NotFound();
                    }
                    else
                    {
                        throw;
                    }
                }
                return RedirectToAction(nameof(Index));
            }

            ViewBag.ItemName = new SelectList(_inventoryDb.Inventory, "ItemName", "ItemName", customer.itemName);
            return View(customer);
        }


        [HttpGet]
        public async Task<IActionResult> SyncCustomerToInventory()
        {
            var customers = _context.Customers.ToList();

            foreach (var customer in customers)
            {
                var inventoryItem = _inventoryDb.Inventory.FirstOrDefault(i => i.ItemName == customer.itemName);

                if (inventoryItem == null)
                {
                    _inventoryDb.Inventory.Add(new Inventory
                    {
                        ItemName = customer.itemName,
                        Quantity = customer.Quantity,
                    });
                }
                else
                {
                 
                    int quantityDifference = customer.Quantity - customer.LastSyncedQuantity;

                    if (inventoryItem.Quantity >= quantityDifference)
                    {
                        inventoryItem.Quantity -= quantityDifference;
                    }
                    else
                    {
                        inventoryItem.Quantity = 0; 
                        ModelState.AddModelError("", $"Insufficient stock for {customer.itemName}. Inventory quantity set to 0.");
                    }
                }
                customer.LastSyncedQuantity = customer.Quantity;
            }

            await _inventoryDb.SaveChangesAsync();
            await _context.SaveChangesAsync(); 
            return RedirectToAction("Index", "Home");
        }

        [HttpGet]
        public IActionResult GetInventoryQuantity(string itemName)
        {
            var inventoryItem = _inventoryDb.Inventory.FirstOrDefault(i => i.ItemName == itemName);
            if (inventoryItem != null)
            {
                return Json(new { quantity = inventoryItem.Quantity });
            }
            return Json(new { quantity = 0 });
        }

        // GET: Customers/Delete/5
        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var customer = await _context.Customers
                .FirstOrDefaultAsync(m => m.Id == id);
            if (customer == null)
            {
                return NotFound();
            }

            return View(customer);
        }

        // POST: Customers/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            var customer = await _context.Customers.FindAsync(id);
            if (customer != null)
            {
                _context.Customers.Remove(customer);
            }

            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool CustomerExists(int id)
        {
            return _context.Customers.Any(e => e.Id == id);
        }
    }
}
