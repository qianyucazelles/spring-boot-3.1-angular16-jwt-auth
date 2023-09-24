import { AxiosService } from './../axios.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent {
  componentToShow: String = "welcome";

  constructor(private axiosService: AxiosService){}
  
  showComponent(componentToShow: string): void{
    this.componentToShow = componentToShow;
  }
  onLogin(input: any): void{
    this.axiosService.request(
      "POST",
      "/login",
      {
        login: input.login,
        password: input.password

      }
    ).then(response=>{
      this.axiosService.setAuthToken(response.data.token);
      this.componentToShow ="messages";
    })
  }

  onRegister(input: any): void{
    this.axiosService.request(
      "POST",
      "/register",
      {
        login: input.login,
        nom: input.nom,
        prenom: input.prenom,
        password: input.password

      }
    ).then(response=>{
      this.axiosService.setAuthToken(response.data.token);
      this.componentToShow ="messages";
    })
  }


}
