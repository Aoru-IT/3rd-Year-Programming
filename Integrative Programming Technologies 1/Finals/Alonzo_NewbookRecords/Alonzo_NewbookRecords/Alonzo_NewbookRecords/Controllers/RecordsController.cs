using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using Alonzo_NewbookRecords.Data;
using Alonzo_NewbookRecords.Models;

namespace Alonzo_NewbookRecords.Controllers
{
    public class RecordsController : Controller
    {
        private readonly Alonzo_NewbookRecordsContext _context;

        public RecordsController(Alonzo_NewbookRecordsContext context)
        {
            _context = context;
        }

        // GET: Records
        public async Task<IActionResult> Index()
        {
            return View(await _context.Records.ToListAsync());
        }

        // GET: Records/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var records = await _context.Records
                .FirstOrDefaultAsync(m => m.Id == id);
            if (records == null)
            {
                return NotFound();
            }

            return View(records);
        }

        // GET: Records/Create
        public IActionResult Create()
        {
            return View();
        }

        // POST: Records/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("Id,FirstName,LastName,Email,PhoneNumber,College,PrelimCheck,MidtermCheck,FinalCheck,ScholarCheck,PendingFee,TotalFee")] Records records)
        {
            if (ModelState.IsValid)
            {
                records.PendingCalculation();
                records.ScholarDeduction();

                _context.Add(records);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            return View(records);
        }

        // GET: Records/Edit/5
        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var records = await _context.Records.FindAsync(id);
            if (records == null)
            {
                return NotFound();
            }
            return View(records);
        }

        // POST: Records/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("Id,FirstName,LastName,Email,PhoneNumber,College,PrelimCheck,MidtermCheck,FinalCheck,ScholarCheck,PendingFee,TotalFee")] Records records)
        {
            if (id != records.Id)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    records.PendingCalculation();
                    records.ScholarDeduction();

                    _context.Update(records);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!RecordsExists(records.Id))
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
            return View(records);
        }

        // GET: Records/Delete/5
        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var records = await _context.Records
                .FirstOrDefaultAsync(m => m.Id == id);
            if (records == null)
            {
                return NotFound();
            }

            return View(records);
        }

        // POST: Records/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            var records = await _context.Records.FindAsync(id);
            if (records != null)
            {
                _context.Records.Remove(records);
            }

            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool RecordsExists(int id)
        {
            return _context.Records.Any(e => e.Id == id);
        }
    }
}
