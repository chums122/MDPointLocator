package us.magicaldreams.mdpointlocator.commands.subcommands.point;

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

public class HelpSubCommand implements MDSubCommand {

    @Override
    public void onCommand(CommandSender sender, Command command, String[] args) {
        Map<String, MDSubCommand> commands = PointBaseCommand.getCommands();
        ArrayList<String> processedEntries = new ArrayList<>();

        sendHelpPageHeader(sender);
        processedEntries.add(CommonUtil.getHelpEntry("/point", "The base command for point locator."));

        for (Map.Entry<String, MDSubCommand> entry : commands.entrySet()) {
            if (sender.hasPermission(entry.getValue().getPermission())) {
                processedEntries.add(CommonUtil.getHelpEntry(entry.getValue().getUsage(), entry.getValue().getDescription()));
            }
        }

        float processedEntriesNo = processedEntries.size();
        int maxEntriesPerPage = 3;
        float pagesRaw = processedEntriesNo / maxEntriesPerPage;
        int pages = (int) Math.ceil(pagesRaw);

        int pageNo = 1;

        if (args.length > 1) {
            try {
                pageNo = Integer.parseInt(args[1]);
            } catch (NumberFormatException ignored) {
                //
            }
        }

        if (pageNo > pages) pageNo = pages;
        if (pageNo < 1) pageNo = 1;

        int pageEntries = pageNo * maxEntriesPerPage;

        for (int ii = pageEntries + maxEntriesPerPage; ii > pageEntries; pageEntries++) {
            try {
                sender.sendMessage(processedEntries.get(pageEntries - maxEntriesPerPage));
            } catch (IndexOutOfBoundsException ignored) {
                break;
            }
        }

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
        TextComponent prefix = new TextComponent("------------");
        prefix.setColor(ChatColor.GRAY);
        prefix.setStrikethrough(true);

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

        TextComponent pageNoMsg = new TextComponent("Page");
        TextComponent pageNoMsg1 = new TextComponent(" " + pageNo + " ");
        pageNoMsg1.setBold(true);
        TextComponent pageNoMsg2 = new TextComponent("of");
        TextComponent pageNoMsg3 = new TextComponent(" " + pages + " ");
        pageNoMsg3.setBold(true);
        pageNoMsg.addExtra(pageNoMsg1);
        pageNoMsg.addExtra(pageNoMsg2);
        pageNoMsg.addExtra(pageNoMsg3);

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

        TextComponent suffix = new TextComponent("------------");
        suffix.setColor(ChatColor.GRAY);
        suffix.setStrikethrough(true);

        sender.spigot().sendMessage(prefix, prevPage, pageNoMsg, nextPage, suffix);
    }

    public String getPermission() {
        return CommonUtil.getPermissionNode();
    }

    public String getUsage() {
        return "/point help";
    }

    public String getDescription() {
        return "Displays this help menu.";
    }

}
