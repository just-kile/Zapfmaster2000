package de.kile.zapfmaster2000.rest.impl.core.achievement;

import java.util.ArrayList;
import java.util.List;

import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.AbstractAchievementProcessor;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.local.EmsigesBienchen;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.local.TrinkerDerStunde;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.local.alclevel.CharlieSheen;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.local.alclevel.DavidHasselhoff;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.local.alclevel.HankMoody;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.local.alclevel.HaraldJunke;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.local.alclevel.LindsayLohan;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.local.degree.Bierbachelor;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.local.degree.Bierdiplomand;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.local.degree.Biermaster;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.timed.Abstinenzler;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.timed.DerLetzteKunde;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.total.DerFrueheVogelTrinktBier;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.total.FirstBlood;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.total.global.EsGehtSeinenGang;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.total.global.GluecksPils;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.total.global.Halbzeit;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.total.global.ThisIsSparta;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.total.global.VeniVidiBieri;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.total.hour.MegaKill;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.total.hour.MonsterKill;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.total.hour.UltraKill;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.total.unique.HomoFaber;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.total.unique.Nimmersatt;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.total.user.Barney;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.total.user.Carl;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.total.user.Homer;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.total.user.Lenny;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.total.user.Moe;

public class AchievementRegistry {

	// TODO: extract to XML or whatever
	public List<AchievementDescriptor> createDescriptors() {
		List<AchievementDescriptor> descriptors = new ArrayList<>();
		descriptors.add(createDescriptor("Moe", "Ein Bier gezapft.",
				"images/achievements/moe.png", Moe.class));
		descriptors.add(createDescriptor("Carl", "Fünf Bier gezapft.",
				"images/achievements/carl.png", Carl.class));

		descriptors.add(createDescriptor("Lenny", "Zehn Bier gezapft.",
				"images/achievements/lenny.png", Lenny.class));

		descriptors.add(createDescriptor("Homer", "15 Bier gezapft.",
				"images/achievements/homer.png", Homer.class));

		descriptors.add(createDescriptor("Barney", "25 Bier gezapft.",
				"images/achievements/barney.png", Barney.class));

		descriptors.add(createDescriptor("Glückspils",
				"Insgesamt 25. Bier gezapft.",
				"images/achievements/glueckspils.png", GluecksPils.class));

		descriptors.add(createDescriptor("Es geht seinen Gang",
				"Insgesamt 50. Bier gezapft.",
				"images/achievements/esgehtseinengang.png",
				EsGehtSeinenGang.class));
		descriptors.add(createDescriptor("Veni, Vidi, Bieri",
				"Insgesamt 100. Bier gezapft.",
				"images/achievements/venividibieri.png", VeniVidiBieri.class));
		descriptors.add(createDescriptor("Halbzeit",
				"Insgesamt 200. Bier gezapft.",
				"images/achievements/halbzeit.png", Halbzeit.class));
		descriptors.add(createDescriptor("This Is Sparta",
				"Insgesamt 300. Bier gezapft.",
				"images/achievements/thisissparta.png", ThisIsSparta.class));

		descriptors.add(createDescriptor("Ultra Kill",
				"Drei Bier innerhalb von einer Stunde gezapft.",
				"images/achievements/ultrakill.png", UltraKill.class));
		descriptors.add(createDescriptor("Mega Kill",
				"Zwei Bier innerhalb von einer Stunde gezapft.",
				"images/achievements/megakill.png", MegaKill.class));
		descriptors.add(createDescriptor("Monster Kill",
				"Vier Bier innerhalb von einer Stunde gezapft.",
				"images/achievements/monsterkill.png", MonsterKill.class));

		descriptors.add(createDescriptor("Trinker der Stunde",
				"Einmal Trinker der Stunde gewesen.",
				"images/achievements/trinkerderstunde.png",
				TrinkerDerStunde.class));

		descriptors.add(createDescriptor("Lindsay Lohan",
				"Alkoholspiegel von einem Promille erreicht.",
				"images/achievements/lindsaylohan.png", LindsayLohan.class));
		descriptors.add(createDescriptor("Hank Moody",
				"Alkoholspiegel von zwei Promille erreicht.",
				"images/achievements/hankmoody.png", HankMoody.class));
		descriptors.add(createDescriptor("Charlie Sheen",
				"Alkoholspiegel von drei Promille erreicht.",
				"images/achievements/charliesheen.png", CharlieSheen.class));
		descriptors.add(createDescriptor("David Hasselhoff",
				"Alkoholspiegel von vier Promille erreicht.",
				"images/achievements/davidhasselhoff.png",
				DavidHasselhoff.class));
		descriptors.add(createDescriptor("Harald Juhnke",
				"Alkoholspiegel von fünf Promille erreicht.",
				"images/achievements/haraldjuhnke.png", HaraldJunke.class));

		descriptors.add(createDescriptor("Emsiges Bienchen",
				"Fünf mal hintereinander jede Stunde ein Bier gezapft.",
				"images/achievements/emsigesbienchen.png",
				EmsigesBienchen.class));

		descriptors.add(createDescriptor("Bierbachelor",
				"Vier Tage am Stück mindestens drei Bier gezapft.",
				"images/achievements/bierbachelor.png", Bierbachelor.class));
		descriptors.add(createDescriptor("Biermaster",
				"Vier Tage am Stück mindestens vier Bier gezapft.",
				"images/achievements/biermaster.png", Biermaster.class));
		descriptors.add(createDescriptor("Bierdiplomand",
				"Vier Tage am Stück mindestens fünf Bier gezapft.",
				"images/achievements/bierdiplomand.png", Bierdiplomand.class));

		descriptors.add(createDescriptor("Abstinenzler",
				"Einen Tag lang nichts gezapft.",
				"images/achievements/abstinenzler.png", Abstinenzler.class));
		descriptors
				.add(createDescriptor("Der letzte Kunde",
						"Letztes Bier an einem Abend gezapft.",
						"images/achievements/derletztekunde.png",
						DerLetzteKunde.class));

		descriptors.add(createDescriptor("Nimmersatt",
				"Ein Liter am Stück gezapft.",
				"images/achievements/nimmersatt.png", Nimmersatt.class));
		descriptors.add(createDescriptor("Homo Faber",
				"Zwei Liter am Stück gezapft.",
				"images/achievements/homofaber.png", HomoFaber.class));

		descriptors.add(createDescriptor("Der frühe Vogel trinkt Bier",
				"Erstes Bier vor Mittag gezapft.",
				"images/achievements/derfruehevogeltrinktbier.png",
				DerFrueheVogelTrinktBier.class));
		descriptors.add(createDescriptor("First Blood",
				"Erster Zapfvorgang aus einem neuen Fass getätigt.",
				"images/achievements/firstblood.png", FirstBlood.class));

		return descriptors;
	}

	private AchievementDescriptor createDescriptor(String pName,
			String pDescription, String pImagePath,
			Class<? extends AbstractAchievementProcessor> pProcessorClass) {
		AchievementDescriptor desc = new AchievementDescriptor();
		desc.setName(pName);
		desc.setDescription(pDescription);
		desc.setImagePath(pImagePath);
		desc.setProcessor(pProcessorClass);
		return desc;
	}

}
