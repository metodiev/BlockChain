 
timeout 15
@echo off
setx GPU_FORCE_64BIT_PTR 0
setx GPU_MAX_HEAP_SIZE 100
setx GPU_USE_SYNC_OBJECTS 1
setx GPU_MAX_ALLOC_PERCENT 100
setx GPU_SINGLE_ALLOC_PERCENT 100

Academy School of Blockchain – http://kingsland.academy Page 7 of 15

EthDcrMiner64.exe -epool eth-eu1.nanopool.org:9999 -ewal
YOUR_WALLET/YOUR_WORKER/YOUR_EMAIL -epsw x -dpool stratum+tcp://sia-
eu1.nanopool.org:7777 -dwal YOUR_WALLET/YOUR_WORKER/YOUR_EMAIL -dcoin sia -asm 1
-dcri 20 -ethi 8 -allpools 1 -dbg 1 -r 1 -wd 1 -minspeed 15 -gser 2 -tt 69 -ttdcr
80 -ttli 85 -tstop 91