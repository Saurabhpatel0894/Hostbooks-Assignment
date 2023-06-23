import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ApiService } from '../services/api.service';

interface Employee {
  emp_id: number;
  firstName: string;
  lastName: string;
  email: string;
}

const EMPLOYEE: Employee[] = [];

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css'],
})
export class EmployeeComponent implements OnInit {
  employeeList = EMPLOYEE;

  closeResult = '';

  employeeForm!: FormGroup;

  empActionIndex!: number;

  mode: string = 'Add';

  constructor(
    private modalService: NgbModal,
    private fb: FormBuilder,
    private apiService: ApiService
  ) {}

  ngOnInit(): void {
    this.getEmployeeList();
    this.employeeForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
    });
  }

  public getEmployeeList(): any {
    this.apiService.getEmployeeList().subscribe(
      (res: any) => {
        if (Array.isArray(res) && res.length) {
          this.employeeList = res;
        } else {
          this.employeeList = [];
        }
      },
      (error) => {
        console.log('error while fetching emp list', error);
      }
    );
  }

  public addEmployee(content: any) {
    this.mode = 'Add';
    this.employeeForm.reset();
    this.modalService.open(content);
  }

  public tableAction(
    content: any,
    formValue: any,
    index: number,
    mode: boolean
  ) {
    mode ? (this.mode = 'Update') : (this.mode = 'View');
    this.empActionIndex = index;
    this.employeeForm.reset();
    this.setEmployeeForm(formValue);
    this.modalService.open(content);
  }

  public setEmployeeForm(formValue: any) {
    this.employeeForm.patchValue({
      firstName: formValue.firstName,
      lastName: formValue.lastName,
      email: formValue.email,
    });
  }

  deleteEmployee(index: number, emp_id: number): any {
    this.apiService.deleteEmployee(emp_id).subscribe(
      (res: any) => {
        console.log('deleted successfully!', res);
        res && this.employeeList.splice(index, 1);
      },
      (error) => {
        console.log('error while deleting', error);
      }
    );
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  employeeFormSubmit(formValue: any, isValid: boolean): void {
    if (!isValid) return;
    const obj: any = {
      firstName: formValue.firstName,
      lastName: formValue.lastName,
      email: formValue.email,
    };
    console.log('form obj', obj);
    this.mode == 'Add' ? this.createEmployee(obj) : this.updateEmployee(obj);
  }

  createEmployee(obj: any): any {
    this.apiService.createEmployee(obj).subscribe(
      (res: any) => {
        console.log('employee added successfully', res);
        this.employeeList.unshift(res);
        this.modalService.dismissAll();
      },
      (error) => {
        console.log('error', error);
      }
    );
  }

  updateEmployee(obj: any): any {
    const editObj = {
      ...obj,
      emp_id: this.employeeList[this.empActionIndex]?.emp_id,
    };
    this.apiService.editEmployee(editObj).subscribe(
      (res: any) => {
        console.log('employee updated successfully', res);
        if (res) {
          Object.assign(this.employeeList[this.empActionIndex], res);
          this.modalService.dismissAll();
        }
      },
      (error) => {
        console.log('error', error);
      }
    );
  }
}
