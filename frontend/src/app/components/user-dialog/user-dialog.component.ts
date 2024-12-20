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
  selectedRole: string = '';
 

  constructor(
    public dialogRef: MatDialogRef<UserDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.isEdit = !!data.user;
    this.user = { ...data.user };
    this.rolesString = this.user.roles?.map((role: any) => role.name).join(', ') || '';
  }

  save(): void {
    // Vérifiez que selectedRole est défini
    if (!this.selectedRole || (this.selectedRole !== 'ADMIN' && this.selectedRole !== 'USER')) {
      alert('Invalid role selected. Only "ADMIN" or "USER" are allowed.');
      return;
    }
  
    // Créez l'objet rôle avec l'ID et le nom
    const roleObject = {
      id: this.selectedRole === 'ADMIN' ? 1 : 2,
      name: this.selectedRole,
    };
  
    if (this.isEdit) {
      // Validation pour l'édition
      if (!this.user.id) {
        alert('Missing user ID for editing.');
        return;
      }
  
      // Assignation des rôles avec l'objet sélectionné
      this.user = {
        ...this.user,
        roles: [roleObject], // Utilisez un tableau contenant le rôle pour l'édition
      };
    } else {
      // Validation pour la création
      if (!this.user.password) {
        alert('Password is required for creating a new user.');
        return;
      }
  
      // Assignation du rôle sans l'ID
      this.user = {
        ...this.user,
        password: this.user.password,
        role: [this.selectedRole], // Utilisez un tableau contenant le nom du rôle
      };
    }
  
    // Fermez le dialogue et retournez les données à la vue parente
    this.dialogRef.close(this.user);
  }
  
  
  
}
