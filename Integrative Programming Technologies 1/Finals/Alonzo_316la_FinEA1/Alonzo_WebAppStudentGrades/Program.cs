using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.DependencyInjection;
using Alonzo_WebAppStudentGrades.Data;
var builder = WebApplication.CreateBuilder(args);
builder.Services.AddDbContext<Alonzo_WebAppStudentGradesContext>(options =>
    options.UseSqlServer(builder.Configuration.GetConnectionString("Alonzo_WebAppStudentGradesContext") ?? throw new InvalidOperationException("Connection string 'Alonzo_WebAppStudentGradesContext' not found.")));

// Add services to the container.
builder.Services.AddControllersWithViews();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Home/Error");
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}

app.UseHttpsRedirection();
app.UseStaticFiles();

app.UseRouting();

app.UseAuthorization();

app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Users}/{action=Index}/{id?}");

app.Run();
