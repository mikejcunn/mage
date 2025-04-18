package mage.cards.f;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.OneOrMoreCombatDamagePlayerTriggeredAbility;
import mage.abilities.common.DiesSourceTriggeredAbility;
import mage.abilities.dynamicvalue.common.SourcePermanentPowerValue;
import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.effects.common.LoseLifeOpponentsEffect;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.counters.CounterType;
import mage.filter.common.FilterCreaturePermanent;

import java.util.UUID;

/**
 *
 * @author Xanderhall
 */
public final class FaerieBladecrafter extends CardImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent(SubType.FAERIE, "Faeries");

    public FaerieBladecrafter(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{B}");
        
        this.subtype.add(SubType.FAERIE);
        this.subtype.add(SubType.ROGUE);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // Whenever one or more Faeries you control deal combat damage to a player, put a +1/+1 counter on Faerie Bladecrafter.
        this.addAbility(new OneOrMoreCombatDamagePlayerTriggeredAbility(new AddCountersSourceEffect(CounterType.P1P1.createInstance()), filter));
        
        //  When Faerie Bladecrafter dies, each opponent loses X life and you gain X life, where X is its power.
        Ability ability = new DiesSourceTriggeredAbility(new LoseLifeOpponentsEffect(SourcePermanentPowerValue.NOT_NEGATIVE).setText("each opponent loses X life"));
        ability.addEffect(new GainLifeEffect(SourcePermanentPowerValue.NOT_NEGATIVE).setText("and you gain X life, where X is its power"));
        this.addAbility(ability);
    }

    private FaerieBladecrafter(final FaerieBladecrafter card) {
        super(card);
    }

    @Override
    public FaerieBladecrafter copy() {
        return new FaerieBladecrafter(this);
    }
}
