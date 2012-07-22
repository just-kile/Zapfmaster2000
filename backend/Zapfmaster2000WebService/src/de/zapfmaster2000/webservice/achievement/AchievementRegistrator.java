package de.zapfmaster2000.webservice.achievement;

import de.zapfmaster2000.webservice.achievement.local.EmsigesBienchen;
import de.zapfmaster2000.webservice.achievement.local.TrinkerDerStunde;
import de.zapfmaster2000.webservice.achievement.local.alclevel.CharlieSheen;
import de.zapfmaster2000.webservice.achievement.local.alclevel.DavidHasselhoff;
import de.zapfmaster2000.webservice.achievement.local.alclevel.HankMoody;
import de.zapfmaster2000.webservice.achievement.local.alclevel.HaraldJunke;
import de.zapfmaster2000.webservice.achievement.local.alclevel.LindsayLohan;
import de.zapfmaster2000.webservice.achievement.local.degree.Bierbachelor;
import de.zapfmaster2000.webservice.achievement.local.degree.Bierdiplomand;
import de.zapfmaster2000.webservice.achievement.local.degree.Biermaster;
import de.zapfmaster2000.webservice.achievement.timed.Abstinenzler;
import de.zapfmaster2000.webservice.achievement.timed.DerLetzteKunde;
import de.zapfmaster2000.webservice.achievement.total.DerFrueheVogelTrinktBier;
import de.zapfmaster2000.webservice.achievement.total.FirstBlood;
import de.zapfmaster2000.webservice.achievement.total.global.EsGehtSeinenGang;
import de.zapfmaster2000.webservice.achievement.total.global.GluecksPils;
import de.zapfmaster2000.webservice.achievement.total.global.Halbzeit;
import de.zapfmaster2000.webservice.achievement.total.global.ThisIsSparta;
import de.zapfmaster2000.webservice.achievement.total.global.VeniVidiBieri;
import de.zapfmaster2000.webservice.achievement.total.hour.MegaKill;
import de.zapfmaster2000.webservice.achievement.total.hour.MonsterKill;
import de.zapfmaster2000.webservice.achievement.total.hour.UltraKill;
import de.zapfmaster2000.webservice.achievement.total.unique.HomoFaber;
import de.zapfmaster2000.webservice.achievement.total.unique.Nimmersatt;
import de.zapfmaster2000.webservice.achievement.total.user.Barney;
import de.zapfmaster2000.webservice.achievement.total.user.Carl;
import de.zapfmaster2000.webservice.achievement.total.user.Homer;
import de.zapfmaster2000.webservice.achievement.total.user.Lenny;
import de.zapfmaster2000.webservice.achievement.total.user.Moe;

/**
 * SImple utility class listing all achievement available to the system.
 */
public class AchievementRegistrator {

	private static String TYPE_SINGLE = "SINGLE";

	private static String TYPE_GROUP = "GROUP";

	private static AchievementDescriptor descriptors[] = {
			createDescriptor("Moe", "Ein Bier gezapft.",
					"images/achievements/moe.png", new Moe(), true, TYPE_SINGLE),
			createDescriptor("Carl", "Fünf Bier gezapft.",
					"images/achievements/carl.png", new Carl(), true,
					TYPE_SINGLE),
			createDescriptor("Lenny", "Zehn Bier gezapft.",
					"images/achievements/lenny.png", new Lenny(), true,
					TYPE_SINGLE),
			createDescriptor("Homer", "15 Bier gezapft.",
					"images/achievements/homer.png", new Homer(), true,
					TYPE_SINGLE),
			createDescriptor("Barney", "25 Bier gezapft.",
					"images/achievements/barney.png", new Barney(), true,
					TYPE_SINGLE),

			createDescriptor("Glückspils", "Insgesamt 25. Bier gezapft.",
					"images/achievements/glueckspils.png", new GluecksPils(),
					true, TYPE_SINGLE),
			createDescriptor("Es geht seinen Gang",
					"Insgesamt 50. Bier gezapft.",
					"images/achievements/esgehtseinengang.png",
					new EsGehtSeinenGang(), true, TYPE_SINGLE),
			createDescriptor("Veni, Vidi, Bieri",
					"Insgesamt 100. Bier gezapft.",
					"images/achievements/venividibieri.png",
					new VeniVidiBieri(), true, TYPE_SINGLE),
			createDescriptor("Halbzeit", "Insgesamt 200. Bier gezapft.",
					"images/achievements/halbzeit.png", new Halbzeit(), true,
					TYPE_SINGLE),
			createDescriptor("This Is Sparta", "Insgesamt 300. Bier gezapft.",
					"images/achievements/thisissparta.png", new ThisIsSparta(),
					true, TYPE_SINGLE),

			createDescriptor("Ultra Kill",
					"Drei Bier innerhalb von einer Stunde gezapft.",
					"images/achievements/ultrakill.png", new UltraKill(), true,
					TYPE_SINGLE),
			createDescriptor("Mega Kill",
					"Zwei Bier innerhalb von einer Stunde gezapft.",
					"images/achievements/megakill.png", new MegaKill(), true,
					TYPE_SINGLE),
			createDescriptor("Monster Kill",
					"Vier Bier innerhalb von einer Stunde gezapft.",
					"images/achievements/monsterkill.png", new MonsterKill(),
					true, TYPE_SINGLE),

			createDescriptor("Trinker der Stunde",
					"Einmal Trinker der Stunde gewesen.",
					"images/achievements/trinkerderstunde.png",
					new TrinkerDerStunde(), true, TYPE_SINGLE),

			createDescriptor("Lindsay Lohan",
					"Alkoholspiegel von einem Promille erreicht.",
					"images/achievements/lindsaylohan.png", new LindsayLohan(),
					true, TYPE_SINGLE),
			createDescriptor("Hank Moody",
					"Alkoholspiegel von zwei Promille erreicht.",
					"images/achievements/hankmoody.png", new HankMoody(), true,
					TYPE_SINGLE),
			createDescriptor("Charlie Sheen",
					"Alkoholspiegel von drei Promille erreicht.",
					"images/achievements/charliesheen.png", new CharlieSheen(),
					true, TYPE_SINGLE),
			createDescriptor("David Hasselhoff",
					"Alkoholspiegel von vier Promille erreicht.",
					"images/achievements/davidhasselhoff.png",
					new DavidHasselhoff(), true, TYPE_SINGLE),
			createDescriptor("Harald Juhnke",
					"Alkoholspiegel von fünf Promille erreicht.",
					"images/achievements/haraldjuhnke.png", new HaraldJunke(),
					true, TYPE_SINGLE),

			createDescriptor("Emsiges Bienchen",
					"Fünf mal hintereinander jede Stunde ein Bier gezapft.",
					"images/achievements/emsigesbienchen.png",
					new EmsigesBienchen(), true, TYPE_SINGLE),

			createDescriptor("Bierbachelor",
					"Vier Tage am Stück mindestens drei Bier gezapft.",
					"images/achievements/bierbachelor.png", new Bierbachelor(),
					true, TYPE_SINGLE),
			createDescriptor("Biermaster",
					"Vier Tage am Stück mindestens vier Bier gezapft.",
					"images/achievements/biermaster.png", new Biermaster(),
					true, TYPE_SINGLE),
			createDescriptor("Bierdiplomand",
					"Vier Tage am Stück mindestens fünf Bier gezapft.",
					"images/achievements/bierdiplomand.png",
					new Bierdiplomand(), true, TYPE_SINGLE),

			createDescriptor("Abstinenzler", "Einen Tag lang nichts gezapft.",
					"images/achievements/abstinenzler.png", new Abstinenzler(),
					true, TYPE_SINGLE),
			createDescriptor("Der letzte Kunde",
					"Letztes Bier an einem Abend gezapft.",
					"images/achievements/derletztekunde.png",
					new DerLetzteKunde(), true, TYPE_SINGLE),

			createDescriptor("Nimmersatt", "Ein Liter am Stück gezapft.",
					"images/achievements/nimmersatt.png", new Nimmersatt(),
					true, TYPE_SINGLE),
			createDescriptor("Homo Faber", "Zwei Liter am Stück gezapft.",
					"images/achievements/homofaber.png", new HomoFaber(), true,
					TYPE_SINGLE),

			createDescriptor("Der frühe Vogel trinkt Bier",
					"Erstes Bier vor Mittag gezapft.",
					"images/achievements/derfruehevogeltrinktbier.png",
					new DerFrueheVogelTrinktBier(), true, TYPE_SINGLE),
			createDescriptor("First Blood",
					"Erster Zapfvorgang aus einem neuen Fass getätigt.",
					"images/achievements/firstblood.png", new FirstBlood(),
					true, TYPE_SINGLE),

	};

	public static AchievementDescriptor[] getAchievements() {
		return descriptors;

	}

	private static AchievementDescriptor createDescriptor(String pName,
			String pDescription, String pImagePath,
			AbstractAchievementProcessor pProcessor, boolean pPublic,
			String pType) {
		AchievementDescriptor descriptor = new AchievementDescriptor();
		descriptor.setName(pName);
		descriptor.setDescription(pDescription);
		descriptor.setImagepath(pImagePath);
		descriptor.setProcessor(pProcessor);
		descriptor.setPublic(pPublic);
		descriptor.setType(pType);
		return descriptor;
	}

}
