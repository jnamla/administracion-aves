export class BasicSearch
 {
    codigo?: string;
    nombre?: string;

    constructor(codigo = "", nombre = "") {
        this.codigo = codigo;
        this.nombre = nombre;
    }
}