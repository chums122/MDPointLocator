package us.magicaldreams.mdpointlocator.commands.subcommands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import us.magicaldreams.mdpointlocator.command.MDSubCommand;
import us.magicaldreams.mdpointlocator.commands.PointBaseCommand;
import us.magicaldreams.mdpointlocator.util.CommonUtil;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Trevor Chumbley
 * 9/21/2022 - 2:37 PM
 **/

public class PointHelpSubCommand implements MDSubCommand {

    @Override
    public void onCommand(CommandSender sender, Command command, String[] args) {
        Map<String, MDSubCommand> commands = PointBaseCommand.getCommands();
        ArrayList<String> helpEntries = new ArrayList<>();

        // Send help header
        sendHelpPageHeader(sender);

        // Manually add entry for /point base command
        helpEntries.add(CommonUtil.getHelpEntry("/point", "The base command for point locator."));

        // Loop through registered sub commands -- add help entry to helpEntries list if player has permission for the command
        for (Map.Entry<String, MDSubCommand> entry : commands.entrySet()) {
            if (sender.hasPermission(entry.getValue().getPermission())) {
                helpEntries.add(CommonUtil.getHelpEntry(entry.getValue().getUsage(), entry.getValue().getDescription()));
            }
        }

        float helpEntriesSize = helpEntries.size();
        int maxEntriesPerPage = 5;
        float pagesRaw = helpEntriesSize / maxEntriesPerPage;
        int pages = (int) Math.ceil(pagesRaw);
        int pageNo = 1;

        // Check if page number specified when running /point help
        if (args.length > 1) {
            if (CommonUtil.isInteger(args[1])) pageNo = Integer.parseInt(args[1]);
        }

        // If requested page greater than max pages
        if (pageNo > pages) pageNo = pages;
        // If requested page less than 1
        if (pageNo < 1) pageNo = 1;

        int pageEntries = pageNo * maxEntriesPerPage;

        // Loop through current page entries starting at current page number * max number of pages
        for (int ii = pageEntries + maxEntriesPerPage; ii > pageEntries; pageEntries++) {
            try {
                sender.sendMessage(helpEntries.get(pageEntries - maxEntriesPerPage));
            } catch (IndexOutOfBoundsException ignored) {
                break;
            }
        }

        // Send help footer
        sendHelpPageFooter(sender, pageNo, pages);
    }

    private void sendHelpPageHeader(CommandSender sender) {
        sender.sendMessage("");
        sender.sendMessage(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "-------------" +
                ChatColor.RESET + ChatColor.WHITE + ChatColor.BOLD + "[" +
                ChatColor.RESET + ChatColor.GREEN + "MD " +
                ChatColor. AQUA + "Point Locator" +
                ChatColor.WHITE + ChatColor.BOLD + "]" +
                ChatColor.GRAY + ChatColor.STRIKETHROUGH + "-------------");
    }

    private void sendHelpPageFooter(CommandSender sender, int pageNo, int pages) {

        // Decoration
        TextComponent decoration = new TextComponent("------------");
        decoration.setColor(ChatColor.GRAY);
        decoration.setStrikethrough(true);

        // Previous page
        TextComponent prevPage = new TextComponent("[");
        prevPage.setBold(true);
        TextComponent prevPage1 = new TextComponent("<");
        prevPage1.setColor(ChatColor.GOLD);
        if ((pageNo - 1) < 1) prevPage1.setColor(ChatColor.DARK_GRAY);
        prevPage1.setBold(false);
        TextComponent prevPage2 = new TextComponent("] ");
        prevPage2.setBold(true);
        prevPage.addExtra(prevPage1);
        prevPage.addExtra(prevPage2);
        prevPage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/point help " + (pageNo - 1)));
        prevPage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Previous Page").create()));

        // Display Page # of #
        TextComponent pageNoMsg = new TextComponent("Page");
        TextComponent pageNoMsg1 = new TextComponent(" " + pageNo + " ");
        pageNoMsg1.setBold(true);
        TextComponent pageNoMsg2 = new TextComponent("of");
        TextComponent pageNoMsg3 = new TextComponent(" " + pages + " ");
        pageNoMsg3.setBold(true);
        pageNoMsg.addExtra(pageNoMsg1);
        pageNoMsg.addExtra(pageNoMsg2);
        pageNoMsg.addExtra(pageNoMsg3);

        // Next page
        TextComponent nextPage = new TextComponent("[");
        nextPage.setBold(true);
        TextComponent nextPage1 = new TextComponent(">");
        nextPage1.setColor(ChatColor.GOLD);
        if ((pageNo + 1) > pages) nextPage1.setColor(ChatColor.DARK_GRAY);
        nextPage1.setBold(false);
        TextComponent nextPage2 = new TextComponent("]");
        nextPage2.setBold(true);
        nextPage.addExtra(nextPage1);
        nextPage.addExtra(nextPage2);
        nextPage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/point help " + (pageNo + 1)));
        nextPage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Next Page").create()));

        // Send the message
        sender.spigot().sendMessage(decoration, prevPage, pageNoMsg, nextPage, decoration);
    }

    public String getPermission() {
        return CommonUtil.getPermissionNode();
    }

    public String getUsage() {
        return "/point help (page)";
    }

    public String getDescription() {
        return "Displays this help menu.";
    }

}
