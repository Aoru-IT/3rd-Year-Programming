using System.ComponentModel.DataAnnotations;

namespace AlonzoDivino_EA2.Models
{
    public class Inventory
    {
        [Key]
        public int Id { get; set; }
        [Required]
        public string ItemName { get; set; }
        [Required]
        public double Price { get; set; }
        [Required]
        public int Quantity { get; set; }
        public double TotalPrice => (Price * Quantity);
    }
}
