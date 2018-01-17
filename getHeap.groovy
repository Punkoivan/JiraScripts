def getMemory(String unit='mb') {
    ```
    Parameters: string unit, by default is mb.
    Possible values: gb, kb, b
    Return: a map with a heap information, units
    depends in parameter, by default is Megabytes (1024 Kb)
    ```
    def multiplier = 0.00000095367431640625
    def memory = [:]
    switch(unit) {
        case 'gb':
            multiplier = 0.000000000931322574615478515625
            break
        case 'kb':
            multiplier = 0.0009765625
            break
        case 'b':
            multiplier = 1
            break
    }
    def runtime = new Runtime()
    memory['free'] = (runtime.freeMemory() * multiplier).toFloat().round(2)
    memory['total'] = (runtime.totalMemory() * multiplier).toFloat().round(2)
    memory['used'] = (memory['total'] - memory['free']).round(2)
    return memory
}
getMemory()
