# PTemplate

A Spigot plugin template

## ❓ What is PTemplate

PTemplate is a Spigot plugin template in Kotlin that abstracts a lot of stuff to help you focus more on your code. It comes with a few examples to help you dive in easily.

### 🧰 Features

- [Advanced command handler](###Commands)
- Custom Tab List
- Unit testing
- Kotlin

## 📝 How to use

Just use this repository as a template and start hacking!\
Keep in mind this is not a library, you can freely hack into the core of the template if you want to.

## Commands

This template comes with a command handler that uses reflection and the `BukkitCommand` class, which means:

- You don't need to manually register your command, each class located in the `commands` package is automatically registered at runtime
- You don't need to specify any command in your `plugin.yml`

You may look up an example command such as [SampleCmd](src/main/kotlin/fr/janotlelapin/ptemplate/commands/SampleCmd.kt) if you're not sure how to create your own command.

### Create a command

Simply create a class in the `commands` package that extends the [BaseCommand](src/main/java/fr/janotlelapin/ptemplate/core/commands/BaseCommand.java) class.\
The name of the class should follow this format to be correctly processed by the command handler: `<CommandName>Cmd`, where `<CommandName>` is the name of the command in pascal case. The command can then be run by typing `/commandname` in lower case.\
The generic type of the class defines which type of entity can run the command

```kotlin
// Can only be run by an instance of the Player class, not by the console
class SuperCoolCmd(name: String, plugin: MainPlugin) : BaseCommand<Player>(name, plugin) {}
// Can be run by any instance of CommandSender, including the console and the Player class
class SuperCoolCmd(name: String, plugin: MainPlugin) : BaseCommand<CommandSender>(name, plugin) {}
```

You can specify the properties of your command in the constructor, for example:

```kotlin
init {
  description = "By far the coolest command ever made"
  aliases = List(1) { "sc" }
  usage = "/supercool <player>"
}
```

Now for the fun part: to make your command actually do something when it's called, implement the `run` method:

```kotlin
override fun run(sender: Player, args: Array<out String>): Boolean {
  if (args.isEmpty()) {
    sender.sendMessage("§cNot enough arguments! Usage: $usage")
    return false
  }

  val target = Bukkit.getPlayer(args[0])
  target?.sendMessage("§7Hi §e${target.name}§7, §e${sender.name} wants to let you know you're the coolest!")
}
```

Putting it all together:

```kotlin
class SuperCoolCmd(name: String, plugin: MainPlugin) : BaseCommand<Player>(name, plugin) {
  init {
    description = "By far the coolest command ever made"
    aliases = List(1) { "sc" }
    usage = "/supercool <player>"
  }

  override fun run(sender: Player, args: Array<out String>): Boolean {
    if (args.isEmpty()) {
      sender.sendMessage("§cNot enough arguments! Usage: $usage")
      return false
    }

    val target = Bukkit.getPlayer(args[0])
    target?.sendMessage("§7Hi §e${target.name}§7, §e${sender.name} wants to let you know you're the coolest!")

    return true
  }
}
```

Congratulations, you just made a super cool command!
