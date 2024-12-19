using System.Security.Cryptography.X509Certificates;

namespace Alonzo_NewbookRecords.Models
{
    public class Records
    {
        public int Id { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Email { get; set; }
        public string PhoneNumber { get; set; }
        public string College { get; set; }

        public bool PrelimCheck { get; set; }
        public bool MidtermCheck { get; set; }
        public bool FinalCheck { get; set; }
        public bool ScholarCheck { get; set; }
        public decimal PendingFee { get; set; }
        public decimal TotalFee { get; set; }

        public decimal PendingCalculation()
        {
            PendingFee = 0;

            if (PrelimCheck)
            {
                PendingFee += 10000;
            }

            if (MidtermCheck)
            {
                PendingFee += 15000;
            }

            if (FinalCheck)
            {
                PendingFee += 15000;
            }

            return PendingFee;
        }

        public decimal ScholarDeduction()
        {
            if (ScholarCheck && PendingFee > 0)
            {
                PendingFee -= 10000;
            }

            TotalFee = PendingFee;
            return TotalFee;
        }
    }
}
