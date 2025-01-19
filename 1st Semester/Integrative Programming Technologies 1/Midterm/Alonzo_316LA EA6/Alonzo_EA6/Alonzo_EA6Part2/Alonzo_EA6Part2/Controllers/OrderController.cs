using Microsoft.AspNetCore.Mvc;

namespace Alonzo_EA6Part2.Controllers
{
    public class OrderController : Controller
    {
        [HttpGet]
        public IActionResult Index()
        {
            return View();
        }

        [HttpPost]
        public IActionResult Calculate(Models.Orders order)
        {
          if (ModelState.IsValid)
            {
                decimal Subtotal = order.SubtotalComputation();
                decimal SelectedDiscount = Math.Round(order.DiscountComputation(), 2);
                decimal Total = Math.Round(order.TotalComputation(), 2);

                ViewBag.Subtotal = Subtotal;
                ViewBag.Discount = SelectedDiscount;
                ViewBag.Total = Total;
                return View("Result", order);
            }

            return View("Index", order);
        }
    }
}
