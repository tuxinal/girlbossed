# Girlbossed

A serverside mod for customizing death messsages!

don't ask about the name

## Configuration

the config file is config/girlbossed.json

there are two types of deaths: Entity deaths and other deaths

Entity deaths are deaths that are caused by another entity or mob and they have an extra `attacker` value.

Other deaths don't have an `attacker` value.

A config file would look like something like this:

```json
{
  "entityDeaths": [
    {
      "type": "arrow", // you can see a list of death types below
      "attacker": "minecraft:skeleton",
      "deathMessage": "%1$s was shot by %2$s" // %1$s gets replaced by the player name and %2$s would be the attacker entity's name 
    }
  ],
  "otherDeaths": [
    {
      "type": "fall",
      "deathMessage": "%s couldn't girlboss gravity" // %s gets replaced by player name
    }
  ]
}
```

## Death types

### Entity deaths

* `any`: any deaths that are caused by entities. will override any other configurations
* `sting`
* `mob`: mostly melee damage (llama spit and shulker bullet also count as mob damage)
* `player`
* `arrow`
* `trident`
* `fireworks`
* `onFire`: ghast and blaze fireballs
* `witherSkull`
* `indirectMagic`: harming potions, evoker fangs, guardian beam
* `thorns`
* `thrown`: eggs, enderpearls, snowballs (you can't actually kill with these they are just in the game (except maybe enderpearls, I'm unsure))
* `explosion.player`: Explosions made by entities NOT JUST PLAYERS

### Other deaths

* `inFire`: campfires
* `onFire`: other sources of fire
* `lightningBolt`
* `lava`
* `hotFloor`: magma blocks
* `inWall`: suffocation damage
* `cramming`: entity cramming damage
* `drown`
* `starve`
* `cactus`
* `wither`
* `fall`
* `flyIntoWall`
* `outOfWorld`: /kill and falling into the void
* `anvil`
* `fallingBlock`
* `dragonBreath`
* `sweetBerryBush`
* `freeze`
* `fallingStalactite`
* `stalagmite`
