using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Alonzo_NewbookRecords.Models;

namespace Alonzo_NewbookRecords.Data
{
    public class Alonzo_NewbookRecordsContext : DbContext
    {
        public Alonzo_NewbookRecordsContext (DbContextOptions<Alonzo_NewbookRecordsContext> options)
            : base(options)
        {
        }

        public DbSet<Alonzo_NewbookRecords.Models.Records> Records { get; set; } = default!;
    }
}
