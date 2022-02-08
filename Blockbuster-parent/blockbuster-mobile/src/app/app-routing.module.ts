import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './_features/auth/_guards/auth.guard';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },
  {
    path: 'home',
    loadChildren: () =>
      import('./_pages/home/home.module').then((m) => m.HomePageModule),
  },
  {
    path: 'stores',
    loadChildren: () =>
      import('./_pages/stores/stores.module').then((m) => m.StoresPageModule),
  },
  {
    path: 'actors',
    loadChildren: () =>
      import('./_pages/actors/actors.module').then((m) => m.ActorsPageModule),
  },
  {
    path: 'films',
    loadChildren: () =>
      import('./_pages/films/films.module').then((m) => m.FilmsPageModule),
  },
  {
    path: 'profile',
    loadChildren: () =>
      import('./_pages/users/profile/profile.module').then(
        (m) => m.ProfilePageModule
      ),
  },
  {
    path: 'auth/register',
    loadChildren: () =>
      import('./_pages/register/register.module').then(
        (m) => m.RegisterPageModule
      ),
  },
  {
    path: 'rentals',
    loadChildren: () =>
      import('./_pages/users/rentals/rentals.module').then(
        (m) => m.RentalsPageModule
      ),
  },
  {
    path: 'editProfile/:id',
    loadChildren: () =>
      import('./_pages/users/edit-profile/edit-profile.module').then(
        (m) => m.EditProfilePageModule
      ),
    canActivate: [AuthGuard],
  },
  {
    path: 'payments',
    loadChildren: () =>
      import('./_pages/users/payments/payments.module').then(
        (m) => m.PaymentsPageModule
      ),
  },
  {
    path: 'categories',
    loadChildren: () =>
      import('./_pages/categories/categories.module').then(
        (m) => m.CategoriesPageModule
      ),
  },
  {
    path: 'films-categories/:id',
    loadChildren: () =>
      import('./_pages/films-categories/films-categories.module').then(
        (m) => m.FilmsCategoriesPageModule
      ),
  },
  {
    path: 'film/:id',
    loadChildren: () =>
      import('./_pages/film/film.module').then((m) => m.FilmPageModule),
  },
  {
    path: 'actor/:id',
    loadChildren: () =>
      import('./_pages/actor/actor.module').then((m) => m.ActorPageModule),
  },
  {
    path: 'forgot-password',
    loadChildren: () =>
      import('./_pages/forgot-password/forgot-password.module').then(
        (m) => m.ForgotPasswordPageModule
      ),
  },
  {
    path: 'change-password/:id',
    loadChildren: () =>
      import('./_pages/change-password/change-password.module').then(
        (m) => m.ChangePasswordPageModule
      ),
  },
  {
    path: 'edit-password',
    loadChildren: () =>
      import('./_pages/edit-password/edit-password.module').then(
        (m) => m.EditPasswordPageModule
      ),
  },
  {
    path: 'rental-requests',
    loadChildren: () =>
      import('./_pages/rental-requests/rental-requests.module').then(
        (m) => m.RentalRequestsPageModule
      ),
  },
  {
    path: 'about-us',
    loadChildren: () => import('./about-us/about-us.module').then(m => m.AboutUsPageModule)
  },
  {
    path: 'contact-us',
    loadChildren: () => import('./contact-us/contact-us.module').then( m => m.ContactUsPageModule)
  },

];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules }),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule { }
