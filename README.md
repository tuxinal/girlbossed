# Girlbossed

A serverside mod for customizing death messsages!

Don't ask about the name

## Configuration

The config file is `config/girlbossed.json`

There are two types of deaths: Entity deaths and other deaths

You can set a death messages to only apply to certain players

Entity deaths are deaths that are caused by another entity or mob and they have an extra `attacker` value.

Other deaths don't have an `attacker` value.

A config file would look like something like this:

```json
{
  "entityDeaths": [
    {
      "type": "arrow", // you can see a list of death types below
      "attacker": "minecraft:skeleton",
      "deathMessage": "%1$s was shot by %2$s", // %1$s gets replaced by the player name and %2$s would be the attacker entity's name 
      "players": [] // you can make a death message only apply to certain players, leave empty to apply to all players
    }
  ],
  "otherDeaths": [
    {
      "type": "fall",
      "deathMessage": "%s couldn't girlboss gravity", // %s gets replaced by player name
      "players":[
        "Tuxinal",  // player names are case sensitive
        "steve"     // there can be multiple players
      ]
    }
  ]
}
```

## Death types

### Entity deaths

* `any`: any deaths that are caused by entities. will override any other configurations
* `sting`
* `mob`
* `player`
* `arrow`
* `trident`
* `fireworks`
* `fireball`
* `witherSkull`
* `indirectMagic`: harming potions, evoker fangs, guardian beam
* `thorns`
* `thrown`: eggs, enderpearls, snowballs (you can't actually kill with these they are just in the game)
* `explosion.player`: Explosions made by entities (pretty much just players)
* `sonic_boom`

### Other deaths

* `inFire`: fire damage taken while standing in fire (includes campfires)
* `onFire`: fire damage taken while not being in fire
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
* `explosion` explosions that do not have a specific attacker (dispenser dropping tnt, etc)
