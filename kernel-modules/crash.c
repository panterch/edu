/* Necessary includes for device drivers */
#include <linux/init.h>
#include <linux/module.h>
#include <linux/kernel.h> /* printk() */

MODULE_LICENSE("Dual BSD/GPL");

void crash_init(void) {
  panic("triggered by crash kernel module");
  return;
}

module_init(crash_init);

