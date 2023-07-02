package de.ban.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.ban.Main.BanSystem;

public class Mute_CMD implements CommandExecutor, Listener {

	private ArrayList<String> mutedPlayers = new ArrayList<>();
	
	@EventHandler
	public void handleMutedChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		if(mutedPlayers.contains(player.getName())) {
			player.sendMessage("§cDu bist zur Zeit gemutet überlege warum!!");
			event.setCancelled(true);
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		Player p = (Player) sender;
		if (p.hasPermission("sys.mute")) {
			if (args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				if(target != null) {
					if(!mutedPlayers.contains(target.getName())) {
						mutedPlayers.add(target.getName());
						p.sendMessage("§cDu hast den Spieler §b" + target.getName() + " §cgemutet!");
						target.sendMessage("§cDu wurdest gemutet!!!! Achte auf dein verhalten");
					} else {
						mutedPlayers.remove(target.getName());
						p.sendMessage("§cDu hast den Spieler §b" + target.getName() + " §centmutet!");
						target.sendMessage("§cDein Chat wurde wieder frei gegeben!");
					}
				}else
					p.sendMessage("§cDer Spieler §6" + args[0] + "§c ist nicht auf dem Server.");
			
				
			}
			
			} else {
				p.sendMessage(BanSystem.noPerms);
			
		}
		return false;
		
	}
}
