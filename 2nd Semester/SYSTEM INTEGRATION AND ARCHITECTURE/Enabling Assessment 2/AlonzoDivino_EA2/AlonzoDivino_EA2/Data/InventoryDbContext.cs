using AlonzoDivino_EA2.Models;
using Microsoft.EntityFrameworkCore;

namespace AlonzoDivino_EA2.Data
{
    public class InventoryDbContext:DbContext
    {
        public InventoryDbContext(DbContextOptions<InventoryDbContext> options) : base(options) { }
        public DbSet<Inventory> Inventory { get; set; }
    }
}
