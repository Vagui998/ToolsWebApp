import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { BodyComponent } from './body/body.component';
import { AboutComponent } from './about/about.component';
import { ToolComponent } from './tool/tool.component';
import { InfoToolComponent } from './info-tool/info-tool.component';
import { SearcherComponent } from './searcher/searcher.component';
import { UserFormComponent } from './user-form/user-form.component';
import { ToolFormComponent } from './tool-form/tool-form.component';

// Define las rutas de la aplicación
const routes: Routes = [
  {path:'home', component:BodyComponent},// Ruta para la página de inicio
  {path:'home/form', component:ToolFormComponent},
  {path:'home/form/:id', component:ToolFormComponent},
  {path:'users', component:AboutComponent}, // Ruta para la página Acerca de
  {path:'tools', component:ToolComponent}, // Ruta para la página de herramientas
  {path:'tool/:id', component:InfoToolComponent}, // Ruta para la página de información de una herramienta en particular
  {path:'search/:term', component:SearcherComponent}, // Ruta para la página de resultados de búsqueda
  {path:'login', component:LoginComponent}, // Ruta para la página de inicio de sesión
  {path:'users/form', component:UserFormComponent},
  {path:'users/form/:id', component:UserFormComponent},
  {path:'', pathMatch: 'full', redirectTo: 'home'} // Ruta predeterminada que redirige a la página de inicio

];

@NgModule({
  imports: [RouterModule.forRoot(routes)], // Importa las rutas y configura el enrutamiento para la aplicación
  exports: [RouterModule] // Exporta las rutas para que puedan ser utilizadas por otros módulos de la aplicación
})
export class AppRoutingModule { }
