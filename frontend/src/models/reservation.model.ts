import { Boite } from "./boite.model";
import { Utilisateur } from "./utilisateur.model";

interface Reservation {
    id :number | null | undefined,
    utilisateur : Utilisateur,
    boite : Boite,
    reservation : number 
    
}