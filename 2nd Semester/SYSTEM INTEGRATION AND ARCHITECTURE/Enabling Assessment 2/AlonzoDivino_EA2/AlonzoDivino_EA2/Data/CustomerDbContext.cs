using AlonzoDivino_EA2.Models;
using Microsoft.EntityFrameworkCore;

namespace AlonzoDivino_EA2.Data
{
    public class CustomerDbContext:DbContext
    {
        public CustomerDbContext(DbContextOptions<CustomerDbContext> options) : base(options) { }
        public DbSet<Customer> Customers { get; set; }

    }
}
