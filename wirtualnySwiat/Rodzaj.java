package wirtualnySwiat;

public enum Rodzaj {

    wilk,
    owca,
    lis,
    zolw,
    antylopa,
    trawa,
    mlecz,
    guarana,
    jagody,
    barszcz,
    czlowiek,
    cyberowca;

    @Override
    public String toString() {
        switch(this){
            case wilk:
                return "wilk";
            case owca:
                return "owca";
            case zolw:
                return "zolw";
            case lis:
                return "lis";
            case antylopa:
                return "antylopa";
            case trawa:
                return "trawa";
            case mlecz:
                return "mlecz";
            case jagody:
                return "wilcze jagody";
            case barszcz:
                return "barszcz Sosnowskiego";
            case guarana:
                return "guarana";
            case czlowiek:
                return "Czlowiek";
            case cyberowca:
                return "cyber-owca";
            default:
                return "obcy";
        }
    }
}
