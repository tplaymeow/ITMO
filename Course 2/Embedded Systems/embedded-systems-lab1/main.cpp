#include "hal.h"

#define SWITCHES_COUNT 4
#define LEDS_COUNT 8
#define FRAMES_COUNT 12

int sw_num[SWITCHES_COUNT] = {
  GPIO_PIN_4,
  GPIO_PIN_8,
  GPIO_PIN_10,
  GPIO_PIN_12
};

int leds_num[LEDS_COUNT] = {
  GPIO_PIN_3,
  GPIO_PIN_4,
  GPIO_PIN_5,
  GPIO_PIN_6,
  GPIO_PIN_8,
  GPIO_PIN_9,
  GPIO_PIN_11,
  GPIO_PIN_12
};

bool frames[FRAMES_COUNT][LEDS_COUNT] = {
  {true, true, true, false, false, false, true, true},
  {false, true, true, true, false, true, true, false},
  {false, false, true, true, true, true, false, false},
  {false, false, false, true, true, true, false, false},
  {false, false, true, true, true, true, true, false},
  {false, true, true, false, false, true, true, true},
  {true, true, false, false, true, true, true, false},
  {false, true, true, true, true, true, false, false},
  {false, false, true, true, true, false, false, false},
  {false, true, true, true, true, false, false, false},
  {false, false, false, false, true, true, false, false},
  {false, false, false, false, false, true, true, false},
};

bool is_animation_mode() {
  return
    HAL_GPIO_ReadPin(GPIOE, sw_num[0]) == GPIO_PIN_RESET &&
    HAL_GPIO_ReadPin(GPIOE, sw_num[1]) == GPIO_PIN_SET &&
    HAL_GPIO_ReadPin(GPIOE, sw_num[2]) == GPIO_PIN_RESET &&
    HAL_GPIO_ReadPin(GPIOE, sw_num[3]) == GPIO_PIN_RESET;
}

bool is_pause_switched() {
  return HAL_GPIO_ReadPin(GPIOC, GPIO_PIN_15) == GPIO_PIN_RESET;
}

int next_animation_frame(int current_animation_frame) {
  return (current_animation_frame + 1) % FRAMES_COUNT;
}

void display_swithces_values() {
  HAL_GPIO_WritePin(GPIOD, leds_num[0], HAL_GPIO_ReadPin(GPIOE, sw_num[0]));
  HAL_GPIO_WritePin(GPIOD, leds_num[1], HAL_GPIO_ReadPin(GPIOE, sw_num[1]));
  HAL_GPIO_WritePin(GPIOD, leds_num[2], HAL_GPIO_ReadPin(GPIOE, sw_num[2]));
  HAL_GPIO_WritePin(GPIOD, leds_num[3], HAL_GPIO_ReadPin(GPIOE, sw_num[3]));
  HAL_GPIO_WritePin(GPIOD, leds_num[4], GPIO_PIN_RESET);
  HAL_GPIO_WritePin(GPIOD, leds_num[5], GPIO_PIN_RESET);
  HAL_GPIO_WritePin(GPIOD, leds_num[6], GPIO_PIN_RESET);
  HAL_GPIO_WritePin(GPIOD, leds_num[7], GPIO_PIN_RESET);
}

void display_green_indicator() {
  HAL_GPIO_WritePin(GPIOD, GPIO_PIN_13, GPIO_PIN_SET);
  HAL_GPIO_WritePin(GPIOD, GPIO_PIN_14, GPIO_PIN_RESET);
  HAL_GPIO_WritePin(GPIOD, GPIO_PIN_15, GPIO_PIN_RESET);
}
void display_yellow_indicator() {
  HAL_GPIO_WritePin(GPIOD, GPIO_PIN_13, GPIO_PIN_RESET);
  HAL_GPIO_WritePin(GPIOD, GPIO_PIN_14, GPIO_PIN_SET);
  HAL_GPIO_WritePin(GPIOD, GPIO_PIN_15, GPIO_PIN_RESET);
}
void display_red_indicator() {
  HAL_GPIO_WritePin(GPIOD, GPIO_PIN_13, GPIO_PIN_RESET);
  HAL_GPIO_WritePin(GPIOD, GPIO_PIN_14, GPIO_PIN_RESET);
  HAL_GPIO_WritePin(GPIOD, GPIO_PIN_15, GPIO_PIN_SET);
}

void display_animation_frame(const bool frame[]) {
  for (int i = 0; i < LEDS_COUNT; i++) {
    HAL_GPIO_WritePin(GPIOD, leds_num[i], frame[i] ? GPIO_PIN_SET : GPIO_PIN_RESET);
  }
}

int umain() {
  const int delay = 500;
  int animation_frame = 0;
  bool is_paused = false;

  while (true) {
    bool is_animation = is_animation_mode();

    if (is_animation && is_pause_switched()) {
      is_paused = !is_paused;
    }

    if (is_animation && is_paused) {
      display_red_indicator();
      display_animation_frame(frames[animation_frame]);
      HAL_Delay(delay);
    } else if (is_animation) {
      display_green_indicator();
      display_animation_frame(frames[animation_frame]);
      HAL_Delay(delay);
      animation_frame = next_animation_frame(animation_frame);
    } else {
      display_yellow_indicator();
      display_swithces_values();
      animation_frame = 0;
    }
  }

  return 0;
}