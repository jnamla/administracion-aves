import { Zona } from './zona';

export class Pais {

    cdPais: string;
    dsNombre: string;
    zona: Zona;

    constructor(cdPais: string, dsNombre: string, zona: Zona) {
        this.cdPais = cdPais;
        this.dsNombre = dsNombre;
        this.zona = zona;
    }
}