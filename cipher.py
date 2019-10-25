#!/usr/bin/env python
# coding: utf-8

# In[32]:


import sys 
import tkinter as tk

w = tk.Tk()
w.title("Cipher")
b = tk.Button(w, option=value)

def index_2d(myList, v):
    for i, x in enumerate(myList):
        if v in x:
            return (i, x.index(v))
        
ptype = sys.argv[1]
msg = sys.argv[2]

code = [ ['.', ',', '(', ')', '-', '_', '"', '?', '|', ';'], ['A', 'B', 'C', '<', 'a', 'b', 'c', '>', '2', '#'],
         ['D', 'E', 'F', '[', 'd', 'e', 'f', ']', '3', '*'], ['G', 'H', 'I', '{', 'g', 'h', 'i', '}', '4', '@'],
         ['J', 'K', 'L', 'L', 'j', 'k', 'l', 'l', '5', '^'], ['M', 'N', 'O', 'O', 'm', 'n', 'o', 'o', '6', '\''],
         ['P', 'Q', 'R', 'S', 'p', 'q', 'r', 's', '7', '~'], ['T', 'U', 'V', 'V', 't', 'u', 'v', 'v', '8', '.'],
         ['W', 'X', 'Y', 'Z', 'w', 'x', 'y', 'z', '9', 'shift'], ['!', '=', '+', 'x', '/', '/', '\\', ':', '0', ' '] ]

def decode(msg):
    i = 0;
    out = "";

    length = len(msg);

    if(length % 2 != 0):
        out = 'length not divisble by 2\n'
        msg = msg.split('00')
        for part in msg:
            if(len(part)%2 != 0):
                out = out + 'Error is this part:' + part + '\n'
                
    else:
        for x in range(0,length,2):
            index1 = int(msg[x:x+1]) - 1;
            index2 = int(msg[x+1:x+2]) - 1;
            out = out + code[index1][index2];
    return out

def encode(msg):     
    eout = ""
    for x in range(len(msg)):
        char = msg[x]
        (ind1,ind2) = index_2d(code,char)
        ind1 = ind1 + 1
        ind2 = ind2 + 1
        if ind1 == 10:
            ind1 = 0
        if ind2 == 10:
            ind2 = 0
        eout = eout + str(ind1) + str(ind2);
    return eout
print("Invalid format\n Format is 'cipher [type] [msg]'")

if(len(sys.argv) != 3) or (ptype != '-d' or ptype != '-e'):
    print("Invalid format\n Format is 'cipher [type] [msg]'")
elif(ptype == '-d'):
    decode(msg)
elif(ptype == '-e'):
    encode(msg)









#%%
