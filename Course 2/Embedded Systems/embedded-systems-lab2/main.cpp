#include "hal.h"

#define SWITCHES_COUNT 4
#define LEDS_COUNT 8
#define FRAMES_COUNT 8

const int sw_num[SWITCHES_COUNT] = {
  GPIO_PIN_4,
  GPIO_PIN_8,
  GPIO_PIN_10,
  GPIO_PIN_12
};

const int leds_num[LEDS_COUNT] = {
  GPIO_PIN_3,
  GPIO_PIN_4,
  GPIO_PIN_5,
  GPIO_PIN_6,
  GPIO_PIN_8,
  GPIO_PIN_9,
  GPIO_PIN_11,
  GPIO_PIN_12
};

const bool frames[FRAMES_COUNT][LEDS_COUNT] = {
  {false, false, false, false, false, false, true, true},
  {false, false, false, false, false, true, true, false},
  {false, false, false, false, true, true, false, false},
  {false, false, false, true, true, false, false, false},
  {false, false, true, true, false, false, false, false},
  {false, true, true, false, false, false, false, false},
  {true, true, false, false, false, false, false, false},
  {true, false, false, false, false, false, false, true},
};

int next_animation_frame(int current_animation_frame) {
  return (current_animation_frame + 1) % FRAMES_COUNT;
}

int sw_value() {
  return
    8 * HAL_GPIO_ReadPin(GPIOE, sw_num[0]) +
    4 * HAL_GPIO_ReadPin(GPIOE, sw_num[1]) +
    2 * HAL_GPIO_ReadPin(GPIOE, sw_num[2]) +
    1 * HAL_GPIO_ReadPin(GPIOE, sw_num[3]);
}

int delay_value() {
  return 500 + sw_value() * 150;
}

void display_animation_frame(const bool frame[]) {
  for (int i = 0; i < LEDS_COUNT; i++) {
    HAL_GPIO_WritePin(GPIOD, leds_num[i], frame[i] ? GPIO_PIN_SET : GPIO_PIN_RESET);
  }
}

int frame = 0;

void TIM6_IRQHandler() {
  display_animation_frame(frames[frame]);
  frame = next_animation_frame(frame);
  WRITE_REG(TIM6_ARR, delay_value());
}

int umain() {
  frame = 0;

  registerTIM6_IRQHandler(TIM6_IRQHandler);

  __enable_irq();

  WRITE_REG(TIM6_ARR, delay_value());
  WRITE_REG(TIM6_DIER, TIM_DIER_UIE);
  WRITE_REG(TIM6_PSC, 0);

  WRITE_REG(TIM6_CR1, TIM_CR1_CEN);

  return 0;
}