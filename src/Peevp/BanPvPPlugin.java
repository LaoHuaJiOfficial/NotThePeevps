package Peevp;

import arc.*;
import arc.math.Mathf;
import arc.util.*;
import mindustry.Vars;
import mindustry.core.Logic;
import mindustry.game.EventType;
import mindustry.game.Team;
import mindustry.gen.Groups;
import mindustry.mod.Plugin;

public class BanPvPPlugin extends Plugin{
    @Override
    public void init(){
        Events.on(EventType.WorldLoadEvent.class, e -> {
            if(Vars.headless){
                if(Vars.state.rules.pvp){
                    Groups.player.each(p -> p.sendMessage("FUCK THE PVP"));

                    Groups.build.each(b -> Time.run(Mathf.random(60, 600), b::kill));
                    Groups.unit.each(b -> Time.run(Mathf.random(60, 600), b::kill));
                    Time.run(600f, () -> {
                        Logic.updateGameOver(Team.derelict);
                    });

                    Vars.maps.removeMap(Vars.state.map);
                }
            }
        });
    }
}
