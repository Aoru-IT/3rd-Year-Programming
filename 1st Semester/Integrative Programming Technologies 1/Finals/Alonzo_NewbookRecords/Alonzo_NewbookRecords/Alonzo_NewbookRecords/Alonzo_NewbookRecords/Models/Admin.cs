using Microsoft.AspNetCore.Identity;

namespace Alonzo_NewbookRecords.Models
{
    public class Admin
    {
        private string username = "donanobispacem";
        private string password = "admaiorem";

        public string? inputUser { get; set; }
        public string? inputPass { get; set; }

        public bool AccountAuth(out string errorMessage)
        {
            errorMessage = string.Empty;
            if (string.IsNullOrEmpty(inputUser))
            {
                errorMessage = "Empty Username";
                return false;
            }

            if (inputUser != username)
            {
                errorMessage = "Invalid Username";
                return false;
            }

            if (string.IsNullOrEmpty(inputPass))
            {
                errorMessage = "Empty Password";
                return false;
            }

            if (inputPass != password)
            {
                errorMessage = "Invalid Password";
                return false;
            }

            return true;
        }
    }
}
