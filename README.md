[waka]: https://wakatime.com/badge/user/be67dcc8-512a-42fa-9440-9d76b4a9e8ef/project/14f7c2ee-467e-41cf-919e-b420a9e72604.svg
[version]: https://img.shields.io/badge/version-1.8--1.17-informational
[release]: https://img.shields.io/badge/-release-informational

![version] ![waka] ![release] 

# trcBlock
 Plugin umożliwiający wymianę przedmiotów na bloki.
 
 #### Prezentacja [**KLIK**](https://www.youtube.com/watch?v=os1xwmvwmk8)
 
 #### Konfiguracja
 ```yaml inventory:
  name: "&6Wymiana przedmiotow na bloki"
  size: 27
  filler:
    enable: true
    item: STAINED_GLASS_PANE
    data: 7

changers:
  diamond:
    from:
      item: DIAMOND
      amount: 9
      data: 0
    to:
      item: DIAMOND_BLOCK
      amount: 1
      data: 0
    inventory:
      name: "&bDiament"
      item: DIAMOND
      data: 0
      slot: 13
      lore:
        - "&8× &7Kliknij aby zamienic &6Diamenty&7,"
        - "         &7na &6bloki diamentow&7."
    messages:
      noItem: "&4Blad: &cNie masz wystarczajaco diamentow!"
      changed: "&aGratulacje! &ePrzerobiles &7{ITEMS} &ediamentow na &7{BLOCKS} &ebloki diamentowe&7!"```
