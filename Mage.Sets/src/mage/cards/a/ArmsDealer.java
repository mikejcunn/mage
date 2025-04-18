package mage.cards.a;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.filter.common.FilterControlledPermanent;
import mage.target.common.TargetCreaturePermanent;

/**
 *
 * @author North
 */
public final class ArmsDealer extends CardImpl {

    private static final FilterControlledPermanent filter = new FilterControlledPermanent(SubType.GOBLIN, "Goblin");

    public ArmsDealer(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{2}{R}");
        this.subtype.add(SubType.GOBLIN);
        this.subtype.add(SubType.ROGUE);

        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        // {1}{R}, Sacrifice a Goblin: Arms Dealer deals 4 damage to target creature.
        SimpleActivatedAbility ability = new SimpleActivatedAbility(
                new DamageTargetEffect(4),
                new ManaCostsImpl<>("{1}{R}"));
        ability.addCost(new SacrificeTargetCost(filter));
        ability.addTarget(new TargetCreaturePermanent());
        this.addAbility(ability);
    }

    private ArmsDealer(final ArmsDealer card) {
        super(card);
    }

    @Override
    public ArmsDealer copy() {
        return new ArmsDealer(this);
    }
}
