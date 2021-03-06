package eu.phiwa.dt.flights;

import java.util.Collection;

import eu.phiwa.dt.DragonTravelMain;
import eu.phiwa.dt.objects.Flight;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Waypoint {

	/**
	 * Removes all WaypointMarkers in the server
	 * 
	 */
	public static void removeWaypointMarkersGlobally() {

		Collection<Block> globalmarkers =  DragonTravelMain.globalwaypointmarkers.values();

		for (Block marker: globalmarkers) {
			marker.getWorld().getBlockAt(marker.getX(), marker.getY(), marker.getZ()).getChunk().load(true);
			marker.setType(Material.AIR);
		}
	}
	/** Removes all WaypointMarkers of the specified flight
	 * 
	 * @param flight
	 * 			Flight whose waypoint-markers you want to remove
	 */
	public static void removeWaypointMarkersOfFlight(Flight flight) {
		for (Waypoint wp: flight.waypoints) {
			if(DragonTravelMain.globalwaypointmarkers.containsKey(wp.marker))
				DragonTravelMain.globalwaypointmarkers.remove(wp.marker);

			if(wp.marker == null)
				continue;
			wp.world.getBlockAt(wp.x, wp.y, wp.z).getChunk().load(true);	
			wp.marker.setType(Material.AIR);
		}
	}
	public boolean finalwp = false;
	public Block marker;
	public int x;	

	public int y;	

	public int z;
	
	public World world;

	public void removeMarker() {
		DragonTravelMain.globalwaypointmarkers.remove(this.marker);
		this.marker.setType(Material.AIR);
	}

	public void setMarker(Player player) {
		marker = player.getLocation().getBlock();
		marker.setType(Material.GLOWSTONE);
		DragonTravelMain.globalwaypointmarkers.put(this.marker, this.marker);
	}

	public String toString() {
		return "WP: (" + x + ", " + y + ", " + z + ", " +world.getName() + ", " + finalwp;		
	}
}
