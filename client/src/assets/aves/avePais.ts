import { Pais } from './pais';
import { Ave } from './ave';

export class AvePais {
    ave: Ave;
    pais: Pais;

    constructor(ave: Ave, pais: Pais) {
        this.ave = ave;
        this.pais = pais;
    }
}

