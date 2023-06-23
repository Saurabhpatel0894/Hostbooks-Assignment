import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  apiUrl = 'http://localhost:8087/';

  constructor(private http: HttpClient) {}

  public getEmployeeList(): Observable<{ results: any[] }> {
    return this.http.get<{ results: any[] }>(`${this.apiUrl + 'employees'}`);
  }

  public createEmployee(employeeData: any): Observable<{ results: any[] }> {
    return this.http.post<{ results: any[] }>(
      `${this.apiUrl + 'employees'}`,
      employeeData
    );
  }

  public editEmployee(employeeData: any): Observable<{ results: any[] }> {
    return this.http.put<{ results: any[] }>(
      `${this.apiUrl + 'employees'}`,
      employeeData
    );
  }

  public deleteEmployee(employeeId: any): Observable<{ results: any[] }> {
    return this.http.delete<{ results: any[] }>(
      `${this.apiUrl + 'employees/' + employeeId}`
    );
  }
}
