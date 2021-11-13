import pygame
from pygame.locals import *

pygame.init()

FPS=30
FramePerSecond=pygame.time.Clock()

BLUE  = (0, 0, 255)
RED   = (255, 0, 0)

DISPLAYSURF = pygame.display.set_mode((300,300))
DISPLAYSURF.fill(WHITE)
pygame.draw.rect(DISPLAYSURF, RED, (100, 200, 100, 50), 2)
pygame.draw.rect(DISPLAYSURF, BLUE, (110, 260, 80, 5))

def plane1():
    red=(255,0,0)
    color=red
def plane2():
    blue=(0,0,255)
    color=blue
while plane1_score<5 or plane2_score<5:
    plane1.forward(50)
    plane2.backward(50)
    if plane1_score>5 or plane2_score>5:
        pygame.quit()
        sys.exit()