import {MatButtonModule,MatSidenavModule,MatCheckboxModule,MatTableModule,MatInputModule,MatSelectModule,MatToolbarModule,MatNativeDateModule } from '@angular/material';
import { NgModule } from '@angular/core';
import {MatDatepickerModule} from '@angular/material/datepicker';
 import { DataSource } from '@angular/cdk/table';
@NgModule({
    imports:[MatButtonModule,MatSidenavModule,MatCheckboxModule,MatNativeDateModule ,MatTableModule,MatInputModule,MatDatepickerModule,MatSelectModule,MatToolbarModule],
    exports:[MatButtonModule,MatSidenavModule,MatCheckboxModule,MatNativeDateModule ,MatTableModule,MatInputModule,MatDatepickerModule,MatSelectModule,MatToolbarModule]
})
export class MaterialModule{}