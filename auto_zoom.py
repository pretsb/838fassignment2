import serial
from Tkinter import *
import tkFont
from time import sleep
import math


ser = serial.Serial('/dev/tty.usbmodemfd141')
ser.baudrate = 9600

def update():
    sensor=[0,0,0,0,0]
    sensorOne=''
    sensorTwo=''
    chunk=''
    color="white"
    index = 1

    while 1:
        val = ser.read(ser.inWaiting())
        for char in val:
            if char.isdigit():
               chunk += char

            if char is '.':
               sensor[index]=int(chunk)
               index = index + 1
               chunk=''
               onTwo=True

            if char is '#':
               sensor[index]=int(chunk)
               if sensor[1] < 1:
                  sensor[1] = 1

               #set max size
               if sensor[1] > 300:
                  sensor[1] = 300
               fontSize = int(math.floor(sensor[1] / 3))

               #set Orientation
               if sensor[2] - sensor[3] > 50:
                  orientation="w"
               elif sensor[3] - sensor[2] > 50:
                  orientation="e"
               else:
                  orientation="center"


               # set color
               if sensor[4] > 400:
                  color="black"
                  fg="white"
               else:
                  color="white"
                  fg="black"

               label.configure(anchor=orientation, font=("Helvetica", fontSize), background=color, fg=fg)
               root.configure(background=color)

               print '1 is ', sensor[1], ' and 2 is ', sensor[2], ' and 3 is ', sensor[3], ' and 4 is ', sensor[4]
   
               # reset data 
               sensorOne=''
               sensorTwo=''
               chunk=''
               index=1
               onTwo=False
               root.update()


root=Tk()
root.geometry("500x300")
label = Label(root, anchor="c", text="Hello world, this is a long line. Can you see the end?", pady=20, font=("Helvetica", 16), background="white")
label.pack()



root.after(0,update)    
root.mainloop()
