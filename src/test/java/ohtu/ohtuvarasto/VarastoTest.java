package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    Varasto varasto2;   //Jätetetään kivasti refaktoroinnille mahdollisuuksia...
    Varasto varastoNeg;
    Varasto varastoNeg2;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto2 = new Varasto(10, 20);
        varastoNeg = new Varasto(-10);
        varastoNeg2 = new Varasto(-10, 10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuusJaSaldo() {
        assertEquals(10, varasto2.getTilavuus(), vertailuTarkkuus);
        assertEquals(10, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuttaPienempiSaldo() {
        varasto2 = new Varasto(10, 8);
        assertEquals(8, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void nimiPalautetaanOikein() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }

    @Test
    public void ottamallaLiikaaSaadaanOikeaMaara() {
        assertEquals(10, varasto2.otaVarastosta(20), vertailuTarkkuus);
    }

    @Test
    public void liikaaLaittamallaYlimaarainenKatoaaOikein() {
        varasto.lisaaVarastoon(50);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenVarastoLuodaanOikein() {
        assertEquals(0, varastoNeg.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivisenMaaranLisays() {
        varasto2.lisaaVarastoon(-10);
        assertEquals(10, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivisenMaaranOtto() {
        varasto2.otaVarastosta(-10);
        assertEquals(10, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenVarastoAlkuSaldollaLuodaanOikein() {
        assertEquals(0, varastoNeg2.getSaldo(), vertailuTarkkuus);
        assertEquals(0, varastoNeg2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void VarastoNegatiivisellaAlkuSaldollaLuodaanOikein() {
        varastoNeg2 = new Varasto(10, -10);
        assertEquals(0, varastoNeg2.getSaldo(), vertailuTarkkuus);
        assertEquals(10, varastoNeg2.getTilavuus(), vertailuTarkkuus); // (10, x, x)
    }

}
