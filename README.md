# MD Point Locator 
MD Point Locator is free to download on spigot.
* [Spigot Release Page](https://www.spigotmc.org/resources/mdpointlocator.85065/ "Spigot Plugin Page")

## Useful Links
* **Website** - <https://magicaldreams.us/>
* **Discord Community** - <https://discord.gg/rrTk7ab>

## Requirements
* Java 8 +
* Bukkit 1.12 +

## About MDPointLocator
MD Point Locator is a free spigot plugin developed by MagicalDreamsUS to help Minecraft theme park servers outline to scale using Google Earth.

## Commands
* **/point** - The base point locator command.
    * **Usage** - /point
* **/point help** - View a list of available commands.
    * **Usage** - /point help (page)
* **/point list** - Lists available starting points.
    * **Usage** - /point list
* **/point info** - View info for a starting point.
    * **Usage** - /point info \<name>
* **/point plot** - Plot a point.
    * **Usage** - /point plot \<name> \<length> \<heading>
* **/point create** - Create a new global starting point.
    * **Usage** - /point create \<name> \<x> \<z> (scale)
* **/point remove** - Remove a global starting point.
    * **Usage** - /point remove \<name>
* **/point setmaterial** - Set the material type for the point
  * **Usage** - /point setmaterial \<material>
* **/point sety** - Set the Y value for the point
  * **Usage** - /point sety \<Y value>
* **/point connect** - Make a line between two points
  * **Usage** - /point connect

## Permissions
* **md.point** - Normal point locator operations
* **md.point.create** - Permission to create global starting points.
* **md.point.remove** - Permission to remove global starting points.
* **md.point.setmaterial** - Permission to set the material for a point.
* **md.point.sety** - Permission to set the Y value for a point.
* **md.point.connect** - Permission to create a line between two points.

## License
MD Point Locator is licensed under the GNU General Public License v3.0. Please
see [`LICENSE`](https://github.com/chums122/MDPointLocator/blob/main/LICENSE) for more info.
