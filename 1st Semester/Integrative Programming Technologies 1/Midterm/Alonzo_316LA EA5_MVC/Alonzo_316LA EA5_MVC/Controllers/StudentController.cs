using Microsoft.AspNetCore.Mvc;

namespace Alonzo_316LA_EA5_MVC.Controllers
{
    public class StudentController : Controller
    {
        [HttpGet]
        public IActionResult Index()
        {
            return View();
        }

        [HttpPost]

        public IActionResult Calculate(Models.Student student)
        {
            if (ModelState.IsValid)
            {
                decimal average = student.Average();
                ViewBag.average = average;
                return View("Result", student);
            }
            return View("Index", student);
        }
    }
}
