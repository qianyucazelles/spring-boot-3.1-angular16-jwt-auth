import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {
  
  @Output() onSubmitLoginEvent = new EventEmitter();
  @Output() onSubmitRegisterEvent = new EventEmitter();

  login: string = "";
  password: string = "";
  active: string = "login";
  nom: string = "";
  prenom: string = "";


	onLoginTab(): void {
		this.active = "login";
	}

	onRegisterTab(): void {
		this.active = "register";
	}
  onSubmitLogin(): void{
    this.onSubmitLoginEvent.emit(
      {"login": this.login,
      "password": this.password
    }
    )
   }

   onSubmitRegister(): void{
    this.onSubmitRegisterEvent.emit(
      { "login": this.login,
        "nom":  this.nom,
        "prenom": this.prenom,
        "password":this.password
      }
    )
   }

}
