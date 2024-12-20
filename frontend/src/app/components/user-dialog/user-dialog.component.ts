import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormBuilder, FormGroup, FormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-user-dialog',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './user-dialog.component.html',
  styleUrl: './user-dialog.component.css'
})

export class UserDialogComponent {
  user: any = {};
  rolesString: string = '';
  isEdit: boolean;

  constructor(
    public dialogRef: MatDialogRef<UserDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.isEdit = !!data.user;
    this.user = { ...data.user };
    this.rolesString = this.user.roles?.map((role: any) => role.name).join(', ') || '';
  }

  save(): void {
    // Map rolesString to rolesArray or roleString with proper id assignment
    const rolesArray = this.rolesString
      .split(',')
      .map((roleName: string) => {
        const trimmedRoleName = roleName.trim().toUpperCase();
        return {
          id: trimmedRoleName === 'ADMIN' ? 1 : trimmedRoleName === 'USER' ? 2 : null,
          name: trimmedRoleName,
        };
      });
  
    const rolesNamesArray = this.rolesString
      .split(',')
      .map((roleName: string) => roleName.trim().toUpperCase());
  
    // Validate rolesArray to ensure all roles have valid IDs
    const hasInvalidRoles = rolesArray.some((role) => role.id === null);
    if (hasInvalidRoles) {
      alert('Invalid role(s) detected. Only "ADMIN" or "USER" are allowed.');
      return;
    }
  
    if (this.isEdit) {
      // For edit: use "roles" and include ID
      if (!this.user.id) {
        alert('Missing user ID for editing.');
        return;
      }
  
      this.user = {
        ...this.user,
        roles: rolesArray, // "roles" field for edit
      };
    } else {
      // For create: use "role" and include only role names
      if (!this.user.password) {
        alert('Password is required for creating a new user.');
        return;
      }
  
      this.user = {
        ...this.user,
        password: this.user.password, // Include the password for create
        role: rolesNamesArray, // "role" field for create
      };
    }
  
    // Close the dialog and send the user data back to the parent component
    this.dialogRef.close(this.user);
  }
  
  
}
