export class AdvancedSearch
 {
    zona?: string;
    nombreAve?: string;
    
    constructor(zona = "", nombreAve = "") {
        this.zona = zona;
        this.nombreAve = nombreAve;
    }
}