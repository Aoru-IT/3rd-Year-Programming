using Microsoft.AspNetCore.Mvc;
using System.Security.Cryptography.X509Certificates;

namespace Alonzo_NewbookRecords.Controllers
{
    public class AdminController : Controller
    {
        [HttpGet]
        public IActionResult Index()
        {
            return View();
        }

        [HttpPost]
        public IActionResult Auth(Models.Admin admin)
        {
            if (ModelState.IsValid)
            {
                bool accountAuth = admin.AccountAuth(out string errorMessage);
                if (accountAuth)
                {
                    
                    return RedirectToAction("Index", "Records");
                }
                else
                {
             
                    ModelState.AddModelError(string.Empty, errorMessage);
                    return View("Index", admin);
                }
            }

            return View("Index", admin);
        }

    }
}
