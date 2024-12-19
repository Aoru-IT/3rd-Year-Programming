using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Alonzo_WebAppStudentGrades.Models;

namespace Alonzo_WebAppStudentGrades.Data
{
    public class Alonzo_WebAppStudentGradesContext : DbContext
    {
        public Alonzo_WebAppStudentGradesContext (DbContextOptions<Alonzo_WebAppStudentGradesContext> options)
            : base(options)
        {
        }

        public DbSet<Alonzo_WebAppStudentGrades.Models.User> User { get; set; } = default!;
    }
}
