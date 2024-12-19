namespace Alonzo_316LA_FinEA2.Models
{
    public class Employee
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Department { get; set; }
        public string Position { get; set; }
        public double RatePerDay { get; set; }
        public int HoursWorked { get; set; }

        public double GrossSalary => RatePerDay * HoursWorked;
        public double Deduction => GrossSalary * 0.12;
        public double NetSalary => GrossSalary - Deduction;
    }
}
