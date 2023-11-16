# Four in line game
### Rules:
```
https://en.wikipedia.org/wiki/Connect_Four
```

### To Run:

```
sudo docker build -t four_in_line_game .
```

```
sudo docker run -it four_in_line_game
```
### Game Settings:
- Base: base of the board
- Height: height of the board
- GameMode:
  - A: Win with four Horizontal or Vertical pieces
  - B: Win with four Diagonal pieces
  - C: Win with four Horizontal, Vertical or Diagonal pieces