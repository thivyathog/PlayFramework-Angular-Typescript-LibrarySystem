import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import{ReaderComponent} from './reader/reader.component';
import{AddlibItemComponent} from './addlib-item/addlib-item.component';
import{BorrowComponent} from './borrow/borrow.component';
import{LibItemComponent} from './lib-item/lib-item.component';
import{ReturnComponent} from './return/return.component';
import{DeleteComponent} from'./delete/delete.component';
import{ReserveComponent} from './reserve/reserve.component';
import{LoginComponent} from './login/login.component';
import{ReportComponent} from './report/report.component';
const routes: Routes = [
  {
    path:'',
    component:LoginComponent
  },
 {
    path:'libItem',
    component:LibItemComponent
  },{
  path:'reserve',
  component:ReserveComponent
},
  {
    path:'reader/id',
    component:ReaderComponent
  },
  {
    path:'addlibItem',
    component:AddlibItemComponent
  },
  {
    path:'borrow',
    component:BorrowComponent
  },
  {
    path:'return',
    component:ReturnComponent
  },
  {
    path:'report',
    component:ReportComponent
  },
  {
    path:'delete',
    component:DeleteComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
