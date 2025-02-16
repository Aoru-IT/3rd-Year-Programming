using System.ComponentModel.DataAnnotations;
namespace AlonzoDivino_EA2.Models { 
public class Customer
{
    [Key]
    public int Id { get; set; }
    [Required]
    public string customerName { get; set; }
    [Required]
    public string Address { get; set; }
    [Required]
    public string itemName { get; set; }
    [Required]
    public int Quantity { get; set; }
    public int LastSyncedQuantity { get; set; }
}
}