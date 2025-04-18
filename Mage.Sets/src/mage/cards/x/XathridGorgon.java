package mage.cards.x;

import mage.MageInt;
import mage.ObjectColor;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.RestrictionEffect;
import mage.abilities.effects.common.continuous.AddCardTypeTargetEffect;
import mage.abilities.effects.common.continuous.BecomesColorTargetEffect;
import mage.abilities.effects.common.continuous.GainAbilityTargetEffect;
import mage.abilities.effects.common.counter.AddCountersTargetEffect;
import mage.abilities.keyword.DeathtouchAbility;
import mage.abilities.keyword.DefenderAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.counters.CounterType;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.target.common.TargetCreaturePermanent;

import java.util.UUID;

/**
 * @author jeffwadsworth
 */
public final class XathridGorgon extends CardImpl {

    public XathridGorgon(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{5}{B}");
        this.subtype.add(SubType.GORGON);

        this.power = new MageInt(3);
        this.toughness = new MageInt(6);

        // Deathtouch
        this.addAbility(DeathtouchAbility.getInstance());

        // {2}{B}, {tap}: Put a petrification counter on target creature. It gains defender and becomes a colorless artifact in addition to its other types. Its activated abilities can't be activated.
        Ability ability = new SimpleActivatedAbility(
                new AddCountersTargetEffect(CounterType.PETRIFICATION.createInstance()),
                new ManaCostsImpl<>("{2}{B}"));
        ability.addCost(new TapSourceCost());
        ability.addTarget(new TargetCreaturePermanent());
        ability.addEffect(new GainAbilityTargetEffect(DefenderAbility.getInstance(), Duration.Custom)
                .setText("It gains defender"));
        ability.addEffect(new AddCardTypeTargetEffect(Duration.Custom, CardType.ARTIFACT)
                .setText("and becomes a colorless"));
        ability.addEffect(new BecomesColorTargetEffect(ObjectColor.COLORLESS, Duration.Custom)
                .setText(" artifact in addition to its other types"));
        ability.addEffect(new XathridGorgonCantActivateEffect());
        this.addAbility(ability);

    }

    private XathridGorgon(final XathridGorgon card) {
        super(card);
    }

    @Override
    public XathridGorgon copy() {
        return new XathridGorgon(this);
    }
}

class XathridGorgonCantActivateEffect extends RestrictionEffect {

    XathridGorgonCantActivateEffect() {
        super(Duration.Custom);
        staticText = "Its activated abilities can't be activated";
    }

    private XathridGorgonCantActivateEffect(final XathridGorgonCantActivateEffect effect) {
        super(effect);
    }

    @Override
    public boolean applies(Permanent permanent, Ability source, Game game) {
        return permanent.getId().equals(getTargetPointer().getFirst(game, source));
    }

    @Override
    public boolean isInactive(Ability source, Game game) {
        return getTargetPointer().getFirst(game, source) != null;
    }

    @Override
    public boolean canUseActivatedAbilities(Permanent permanent, Ability source, Game game, boolean canUseChooseDialogs) {
        return false;
    }

    @Override
    public XathridGorgonCantActivateEffect copy() {
        return new XathridGorgonCantActivateEffect(this);
    }

}
