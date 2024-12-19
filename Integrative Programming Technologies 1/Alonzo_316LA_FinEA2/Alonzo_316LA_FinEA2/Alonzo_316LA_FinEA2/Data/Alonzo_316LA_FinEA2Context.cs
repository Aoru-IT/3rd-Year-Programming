using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Alonzo_316LA_FinEA2.Models;

namespace Alonzo_316LA_FinEA2.Data
{
    public class Alonzo_316LA_FinEA2Context : DbContext
    {
        public Alonzo_316LA_FinEA2Context (DbContextOptions<Alonzo_316LA_FinEA2Context> options)
            : base(options)
        {
        }

        public DbSet<Alonzo_316LA_FinEA2.Models.Employee> Employee { get; set; } = default!;
    }
}
